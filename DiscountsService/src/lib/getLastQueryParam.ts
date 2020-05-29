const getLastQueryParam = (url: string) => {
  const splited = url.split("/");
  return splited[splited.length - 1];
};

export default getLastQueryParam;
