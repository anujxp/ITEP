import { Router } from "express";
import { createNote, read, delete1,getOne } from "../controller/noteController.js";

const router = Router();

router.route("/").post(createNote).get(read);
router.route("/:id").delete(delete1);
router.route("/:title").get(getOne);

export default router;