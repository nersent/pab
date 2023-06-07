package com.nersent.pab.product;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  public List<ProductData> cachedList = null;

  private void initCache() {
    try {
      URL url = new URL("https://dummyjson.com/products");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/json");

      if (connection.getResponseCode() != 200) {
          throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
      StringBuilder sb = new StringBuilder();
      String output;
      while ((output = br.readLine()) != null) {
          sb.append(output);
      }

      connection.disconnect();

      // Parse the JSON response
      JSONObject jsonObj = new JSONObject(sb.toString());
      JSONArray jsonArray = jsonObj.getJSONArray("products");
      List<ProductData> products = new ArrayList<>();

      for (int i = 0; i < jsonArray.length(); i++) {
          JSONObject jsonObject = jsonArray.getJSONObject(i);
          Long id = jsonObject.getLong("id");
          String name = jsonObject.getString("title");
          double price = jsonObject.getDouble("price");
          String description = jsonObject.getString("description");
          String image = jsonObject.getString("thumbnail");

          ProductData product = new ProductData(id, name, description, price, image);
          products.add(product);
      }

      this.cachedList = products;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<ProductData> getAll() {
    if (this.cachedList == null) {
      this.initCache();
    }
    return this.cachedList;
  }

  public ProductData getById(Long id) {
    List<ProductData> products = this.getAll();

    for (ProductData product : products) {
      if (product.id.equals(id)) {
        return product;
      }
    }

    return null;
  }
}
