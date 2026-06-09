import ImageKit from 'imagekit'
import { log } from 'node:console';

const imageKit = new ImageKit({
    publicKey: process.env.IMAGEKIT_PUBLIC_KEY,
    privateKey: process.env.IMAGEKIT_PRIVATE_KEY,
    urlEndpoint: process.env.IMAGEKIT_URL_ENDPOINT
});


async function uploadToImageKit(fileBuffer,originalFileName){
    try {
        const response = await imageKit.upload({
            file: fileBuffer.toString('base64'),
            fileName : originalFileName,
            folder: "/post"
        });
        return response.url;
    }catch(err){
       console.error("ImageKit Service Error:", err);
        return null;
    }
}



export default uploadToImageKit