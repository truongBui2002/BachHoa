/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.java;

import dal.ProductDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Product;

/**
 *
 * @author LENOVO
 */
public class CookieCart {

    //Mỗi item sẽ bị ngăn cách bởi dấu "/"
    //productId và quantity sẽ ngăn cách bởi dấu ":"
    public String add(String txt, int pId, int qTy) {
        HashMap<Integer, Integer> list = new HashMap<>();
        String[] it = txt.split("/");
        for (String it1 : it) {
            String[] s = it1.split(":");
            int productId = Integer.parseInt(s[0]);
            int quantity = Integer.parseInt(s[1]);
            list.put(productId, quantity);
        }
        boolean check = true;
        for (Integer i : list.keySet()) {
            if (i == pId) {
                check = false;
                list.put(i, list.get(i) + qTy);
            }
        }
        if (check) {
            list.put(pId, qTy);
        }
        String out = toString(list);
        return out;
    }

    public String update(String txt, int pId, int qTy) {
        HashMap<Integer, Integer> list = new HashMap<>();
        String[] it = txt.split("/");
        for (String it1 : it) {
            String[] s = it1.split(":");
            int productId = Integer.parseInt(s[0]);
            int quantity = Integer.parseInt(s[1]);
            if (productId == pId) {
                list.put(productId, qTy);
            } else {
                list.put(productId, quantity);
            }
        }
        for (Integer i : list.keySet()) {
            if (i == pId) {
                list.put(i, qTy);
            }
        }
        String out = toString(list);
        return out;
    }

    public String delete(String txt, int pId) {
        HashMap<Integer, Integer> list = new HashMap<>();
        String[] it = txt.split("/");
        for (String it1 : it) {
            String[] s = it1.split(":");
            int productId = Integer.parseInt(s[0]);
            int quantity = Integer.parseInt(s[1]);
            if (productId != pId) {
                list.put(productId, quantity);
            }

        }
        String out = toString(list);
        return out;
    }

    public String toString(HashMap<Integer, Integer> list) {
        String out = "";
        for (Integer i : list.keySet()) {
            int quantity = list.get(i);
            if (out.isEmpty()) {
                out += i + ":" + quantity;
            } else {
                out = out + "/" + i + ":" + quantity;
            }
        }
        return out;
    }

    public List<Product> getListcart(String txt) {
        List<Product> list = new ArrayList<>();
        ProductDAO pd = new ProductDAO();
        if (!txt.isEmpty()) {
            String[] it = txt.split("/");
            for (String it1 : it) {
                String[] s = it1.split(":");
                int productId = Integer.parseInt(s[0]);
                int quantity = Integer.parseInt(s[1]);
                Product p = pd.getById(productId);
                p.setQuantity(quantity);
                list.add(p);
            }
        }
        return list;
    }

//    public static void main(String[] args) {
//        String txt = "1:2/2:3/3:2";
//        CookieCart c = new CookieCart();
//        String out = c.update(txt, 2, 7);
//        System.out.println(out);
//    }
}
