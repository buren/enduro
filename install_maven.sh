#!/bin/bash

__enduro-install-maven() {
    current_dir=$(pwd)
    mkdir ~/apps
    cd ~/apps
    curl -O http://apache.mirrors.spacedump.net/maven/maven-3/3.1.1/source/apache-maven-3.1.1-src.zip
    unzip apache-maven-3.1.1-src.zip
    cd apache-maven-3.1.1
    ant
    echo "Intalled maven. Command executed with: ~/apps/maven/apache-maven-3.0-SNAPSHOT/bin/mvn"
    cd $current_dir
}

__enduro-clone() {
    current_dir=$(pwd)
    target_dir="~/eda270_coach/"
    mkdir -p $target_dir
    cd $target_dir && git clone git@github.com:buren/enduro.git
    cd enduro
    ~/apps/maven/apache-maven-3.0-SNAPSHOT/bin/mvn test
    cd $current_dir
}

__enduro-install-maven
__enduro-clone
