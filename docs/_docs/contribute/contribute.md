---
title: Contributing
permalink: /docs/contribute/
---

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

## Writing content

### Docs

Docs are [collections](https://jekyllrb.com/docs/collections/) of pages stored under `_docs` folder. To create a new page:

**1.** Create a new Markdown as `_docs/my-page.md` and write [front matter](https://jekyllrb.com/docs/frontmatter/) & content such as:

```
---
title: My Page
permalink: /docs/my-page/
---

Hello World!
```

**2.** Add the pagename to `_data/docs.yml` file in order to list in docs navigation panel:

```
- title: My Group Title
  docs:
  - my-page
```

### Blog posts

Add a new Markdown file such as `2017-05-09-my-post.md` and write the content similar to other post examples.

### Pages

The homepage is located under `index.html` file. You can change the content or design completely different welcome page for your taste. (You can use [bootstrap components](http://getbootstrap.com/components/))

In order to add a new page, create a new `.html` or `.md` (markdown) file under root directory and link it in `_includes/topnav.html`.
