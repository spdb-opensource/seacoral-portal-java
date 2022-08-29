package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.ConsulChkRsEnum;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.exception.ConnectConsulException;
import com.bsg.upm.exception.ServiceNotFoundException;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.health.HealthConsulClient;
import com.ecwid.consul.v1.health.model.Check;
import com.ecwid.consul.v1.health.model.Check.CheckStatus;
import com.ecwid.consul.v1.health.model.HealthService;

@Service
public class ConsulService extends BaseService {

    public HttpClient getConsulHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).setConnectionRequestTimeout(2000)
                .setSocketTimeout(60000).build();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .useSystemProperties();

        return httpClientBuilder.build();
    }

    public List<String> listServicePassingAddr(List<String> consulAddrs, String serviceNamePrefix)
            throws ConnectConsulException, ServiceNotFoundException {
        List<String> serviceNamePrefixs = new ArrayList<>();
        serviceNamePrefixs.add(serviceNamePrefix);
        Map<String, List<String>> passingServiceAddrsMap = getPassingServiceAddrsMap(consulAddrs, serviceNamePrefixs);
        return passingServiceAddrsMap.get(serviceNamePrefix);
    }

    public Map<String, List<String>> getPassingServiceAddrsMap(List<String> consulAddrs,
            List<String> serviceNamePrefixs) throws ConnectConsulException, ServiceNotFoundException {
        HttpClient httpClient = getConsulHttpClient();
        HealthConsulClient healthClient = null;
        List<Check> healthChecksStates = null;
        Map<String, List<String>> passingServiceAddrsMap = new HashMap<>();

        for (String consulAddr : consulAddrs) {
            String[] ipAndPort = consulAddr.split(":");
            String consulIP = ipAndPort[0];
            int consulPort = Integer.parseInt(ipAndPort[1]);
            try {
                logger.info("Connect to consul {}:{}.", consulIP, consulPort);

                ConsulRawClient rawClient = new ConsulRawClient(consulIP, consulPort, httpClient);
                healthClient = new HealthConsulClient(rawClient);
                healthChecksStates = healthClient.getHealthChecksState(CheckStatus.PASSING, null).getValue();
                break;
            } catch (Exception e) {
                logger.error("Connect to consul {}:{} failed.", consulIP, consulPort);
            }
        }

        if (healthChecksStates == null) {
            String loginfo = loginfo(ConsulChkRsEnum.CONNECT_FAILED, consulAddrs);
            throw new ConnectConsulException(loginfo);
        }

        for (String serviceNamePrefix : serviceNamePrefixs) {
            List<String> passingAddrs = new ArrayList<>();
            passingServiceAddrsMap.put(serviceNamePrefix, passingAddrs);
            for (Check check : healthChecksStates) {
                if (check.getServiceName().startsWith(serviceNamePrefix)) {
                    List<HealthService> healthServices = healthClient
                            .getHealthServices(check.getServiceName(), true, null).getValue();
                    com.ecwid.consul.v1.health.model.HealthService.Service service = healthServices.get(0).getService();
                    passingAddrs.add(service.getAddress() + ":" + service.getPort());
                }
            }
            if (passingAddrs.size() == 0) {
                String loginfo = loginfo(ConsulChkRsEnum.SERVICE_NOT_FOUND, consulAddrs, serviceNamePrefix);
                throw new ServiceNotFoundException(loginfo);
            }
        }
        return passingServiceAddrsMap;
    }

    public Map<String, String> getServiceStatusMap(List<String> consulAddrs, List<String> serviceNamePrefixs) {
        List<Check> healthChecksStates = null;
        HttpClient httpClient = getConsulHttpClient();
        Map<String, String> serviceStatusMap = new HashMap<>();

        for (String consulAddr : consulAddrs) {
            String[] ipAndPort = consulAddr.split(":");
            String consulIP = ipAndPort[0];
            int consulPort = Integer.parseInt(ipAndPort[1]);
            try {
                logger.info("Connect to consul {}:{}.", consulIP, consulPort);

                ConsulRawClient rawClient = new ConsulRawClient(consulIP, consulPort, httpClient);
                HealthConsulClient healthClient = new HealthConsulClient(rawClient);
                healthChecksStates = healthClient.getHealthChecksState(null).getValue();
                break;
            } catch (Exception e) {
                logger.error("Connect to consul {}:{} failed.", consulIP, consulPort);
            }
        }

        if (healthChecksStates == null) {
            String loginfo = loginfo(ConsulChkRsEnum.CONNECT_FAILED, consulAddrs);
            throw new ConnectConsulException(loginfo);
        }

        for (String serviceNamePrefix : serviceNamePrefixs) {
            int serviceCount = 0;
            int servicePassingCount = 0;
            String serviceStatus = DictConsts.STATUS_WARNNING;
            for (Check check : healthChecksStates) {
                if (check.getServiceName().startsWith(serviceNamePrefix)) {
                    serviceCount++;

                    if (check.getStatus() == CheckStatus.PASSING) {
                        servicePassingCount++;
                    }
                }
            }

            if (serviceCount != 0) {
                if (servicePassingCount == serviceCount) {
                    serviceStatus = DictConsts.STATUS_PASSING;
                } else if (servicePassingCount == 0) {
                    serviceStatus = DictConsts.STATUS_CRITICAL;
                }
            }
            serviceStatusMap.put(serviceNamePrefix, serviceStatus);
        }
        return serviceStatusMap;
    }
}
