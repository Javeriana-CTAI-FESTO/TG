const express = require('express');
const app = express();
app.use(express.static('./dist/front'));
app.get('/*', (req, res) =>
    res.sendFile('index.html', {root: 'dist/front/'}),
);
app.listen(4200, () => console.log('Listening on port 4200'));
