const axios = require('axios');
const fs = require('fs');
const path = require('path');
const FormData = require('form-data');

const directoryPath = path.join(__dirname, 'New_Pictures');
const authToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5OTY1NDg1NCwiZXhwIjoxNjk5NjU2MDU0fQ.ebw9L6YiLa3Fnc9TNyhxBkKMug6F0VlzkPO7vqDyh2KJUHoa9PYgGtx5Q2Z8HwJ4pxg-gSeDRZYyd5cq77h-Lg';

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
