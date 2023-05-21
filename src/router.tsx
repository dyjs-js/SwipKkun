import { createBrowserRouter } from "react-router-dom";
import Root from "./componenets/Root";
import Home from "./routes/Home";
import { NotFound } from "./routes/NotFound";
import ArticlDetail from "./routes/ArticleDetail";
import Chat from "./componenets/Chat";

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
        path: "articles/:articlePk",
        element: <ArticlDetail />,
      },
      {
        path: "chat",
        element: <Chat />,
      },
    ],
  },
]);

export default router;
