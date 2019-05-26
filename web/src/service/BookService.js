import axios from 'axios';

const search = async (keyword) => {
  const res = await axios.get(`http://localhost:8080/api/book/search?keyword=${keyword}`);
  const { data } = res.data;

  return data;
}

export default {
  search
};