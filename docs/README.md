
## Getting started

So you want to contribute to this site? Awsome.

This site uses ruby and jekyll to generate static html files from markdown.
[GitHub Pages](https://pages.github.com) support jekyll.

You have 2 options:
* Install the requred software on your own computer
* Use the editor on github so that you can edit the markdown files.

Check out the other links in this folder to get a crash course in markdown and bootstrap.

### Install required software to be able to edit and host local
1. [Install RVM](https://rvm.io/rvm/install) Do not use the ubuntu special package, it contains bugs...
2. [Install Jekyll](https://jekyllrb.com/) 

### Clone the repo

```
git clone https://github.com/knowit/kubernetes-workshop.git
```

The source of the site is located in the docs folder. 

### Host the site local
```
bundle install
bundle update
bundle exec jekyll serve . --watch
```

### Build offline standalone version
Make sure you have docker installed.

Run this terminal commando

```bash
./build-standalone.sh
```

### Run offline standalone docker container
```bash
./run.sh

```
Open browser `http://localhost` or click []this link](http://localhost)
