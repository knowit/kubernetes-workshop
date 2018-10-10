'use strict';

const express = require('express');

// Constants
const PORT = 8080;
const HOST = '0.0.0.0';

// App
const app = express();

app.get('/backendurl', (req, res) => {
  if (process.env.BACKEND_URL === undefined) {
    res.send("(not set)");
  } else {
    res.send(process.env.BACKEND_URL);
  }
});

// Sample endpoint
app.get('/hello', (req, res) => {
  res.send("Hello there!");
});

// Health check
app.get('/health', (req, res) => {
  res.send("{ status: 'OK' }");
});

app.use(express.static('public'))

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);

console.log("BACKEND_URL: " + process.env.BACKEND_URL);