#!/bin/bash
#set -o nounset
VERSION=$1
NAME=web-manager
dir=$(dirname $(readlink -f $0))
temp_dir="/tmp/upm"
installed () {
    command -v "$1" >/dev/null 2>&1
}
# Google Styleguide says error messages should go to standard error.
warn () {
    echo "$@" >&2
}
die () {
    status="$1"
    shift
    warn "$@"
    exit "$status"
}
installed docker || {
    die 2 "Docker not installed"
}
docker build --rm=true -t ${NAME}:${VERSION} ${dir} || {
    die 3 "build image failed!"
}