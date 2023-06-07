const axios = require("axios");

const register = async () => {
  const res = await axios.post(
    `http://localhost:8080/api/auth/register`,
    {
      username: "test",
      password: "test",
    },
    {
      headers: {
        "content-type": "application/json",
      },
    }
  );

  console.log(res.data);
};

const login = async (verbose  = true) => {
  const res = await axios.post(
    `http://localhost:8080/api/auth/login`,
    {
      username: "test",
      password: "test",
    },
    {
      headers: {
        "content-type": "application/json",
      },
    }
  );

  if (verbose) {

  console.log(res.data);
  console.log(res.headers["set-cookie"]);
  }
  
  return res.data;
};

const about = async () => {
  const token = await login(false);

  try {
    const res = await axios.post(
      `http://localhost:8080/api/user/about`,
      {
      },
      {
        headers: {
          "content-type": "application/json",
          "Authorization": `Bearer ${token}`
        },
      }
    );

    console.log(res.data);
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.log("error")
      console.log(error.response?.data);
    } else {
      console.log(error);
    }
  }


};

const main = async () => {
  // await register();
  // await login();
  await about();
};

main();
