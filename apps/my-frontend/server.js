'use strict';

const express = require('express');

// Constants
const PORT = 8080;
const HOST = '0.0.0.0';

// App
const app = express();

app.get('/myvar', (req, res) => {
  res.send(process.env.MY_VAR);
});

app.use(express.static('public'))

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);

console.log("My env: " + process.env.MY_VAR);