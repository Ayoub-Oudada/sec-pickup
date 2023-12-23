import { useParams } from "react-router-dom";

const Show = () => {
  const { parentId } = useParams();

  return <div>Show parent id : {parentId}</div>;
};

export default Show;
