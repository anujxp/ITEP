import {Router} from 'express'
import { createPost ,getAllPosts} from '../controller/PostsController.js';
import { upload } from '../middlewares/multer.middleware.js';



const router = Router();

router.route('/create').post(upload.single("image"),createPost)
router.route("/all").get(getAllPosts);

export default router