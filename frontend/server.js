const fs = require('fs');
const express = require('express');
const path = require('path');
const app = express();
const port = 3000;

// Middlewares
app.use(express.json());

// Helper function to save data to a file
const saveToFile = (filename, data) => {
  fs.writeFile(path.join(__dirname, 'componentes', filename), data, (err) => {
    if (err) throw err;
    console.log(`${filename} has been saved in componentes!`);
  });
};

// Endpoints to save data
app.post('/save', (req, res) => {
  const { type, data } = req.body;

  switch (type) {
    case 'almacenamiento':
      saveToFile('almacenamiento.txt', data);
      break;
    case 'fuente_de_poder':
      saveToFile('fuente_de_poder.txt', data);
      break;
    case 'gabinete':
      saveToFile('gabinete.txt', data);
      break;
    case 'graficas':
      saveToFile('graficas.txt', data);
      break;
    case 'placas_madre':
      saveToFile('placas_madre.txt', data);
      break;
    case 'procesadores':
      saveToFile('procesadores.txt', data);
      break;
    case 'ram':
      saveToFile('ram.txt', data);
      break;
    default:
      res.status(400).send('Invalid type');
      return;
  }

  res.send(`${type} data has been saved!`);
});

// Serve static HTML files in componentes
app.use('/componentes', express.static(path.join(__dirname, 'componentes')));

// Serve index.html for the root route
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'index.html'));
});

// Catch all HTML files in componentes folder
app.get('/componentes/:filename', (req, res) => {
  const filename = req.params.filename;
  const filePath = path.join(__dirname, 'componentes', `${filename}.html`);
  res.sendFile(filePath, (err) => {
    if (err) {
      res.status(404).send('File not found');
    }
  });
});

// Serve HTML files in the root directory
app.get('/:filename', (req, res) => {
  const filename = req.params.filename;
  let filePath = path.join(__dirname, `${filename}.html`);

  // Check if the file exists with .html extension
  if (!fs.existsSync(filePath)) {
    // Check if the file exists without any extension
    filePath = path.join(__dirname, `${filename}`);
  }

  res.sendFile(filePath, (err) => {
    if (err) {
      res.status(404).send('File not found');
    }
  });
});

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
