import { createBrowserRouter } from "react-router-dom";
import Root from "./componenets/Root";
import Home from "./routes/Home";
import { NotFound } from "./routes/NotFound";
import ArticlDetail from "./routes/ArticleDetail";
import Chat from "./chatComponents/Chat";
import UploadArticle from "./routes/UploadArticle";
import Mypage from "./componenets/Mypage";
import Apitest from "./componenets/Apitest";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <NotFound />,
    children: [
      {
        path: "",
        element: <Home />,
      },
      {
        path: "articles/upload",
        element: <UploadArticle />,
      },
      {
        path: "articles/:articlePk",
        element: <ArticlDetail />,
      },
      {
        path: "chat",
        element: <Chat />,
      },

      {
        path: "mypage",
        element: <Mypage />,
      },
      {
        path: "apitest",
        element: <Apitest />,
      },
    ],
  },
]);

export default router;
