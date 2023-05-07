import { useParams } from "react-router-dom";

export default function ArticlDetail() {
  const params = useParams();
  console.log(params);
  return <h1>this is article detail</h1>;
}
