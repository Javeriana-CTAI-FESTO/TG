const axios = require('axios');
const fs = require('fs');
const path = require('path');
const FormData = require('form-data');

const directoryPath = path.join(__dirname, 'New_Pictures');
const authToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5OTAyMTMyNCwiZXhwIjoxNjk5MDIyNTI0fQ.AJtMsSNUg3cYkiTVvmuzExNu4bq1HTarboO1U-rTZIE7lvZQtiJ0_ewF19Yrhs9OY3Sq8Dz0BPKyFlSjFMRPkw';

fs.readdir(directoryPath, async (err, files) => {
    if (err) {
        return console.log('Unable to scan directory: ' + err);
    } 

    for (const file of files) {
        const filePath = path.join(directoryPath, file);
        const form = new FormData();
        form.append('image', fs.createReadStream(filePath));

        try {
            const response = await axios.post('http://localhost:8081/api/admin/storage/image/upload', form, {
                headers: {
                    ...form.getHeaders(),
                    'Authorization': `Bearer ${authToken}`
                }
            });

            console.log(`Image ${file} uploaded successfully`);
        } catch (error) {
            console.error(`Failed to upload image ${file}: ${error}`);
        }
    }
});
