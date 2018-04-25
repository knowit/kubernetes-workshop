#!/usr/bin/env bash
set -e # halt script on error
#building dev
bundle exec jekyll build --config _config.yml,_config-standalone.yml .

docker build -t knowit-k8s-workshop-standalone .