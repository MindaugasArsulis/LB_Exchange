package com.mindaugas.uzdavinys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.Scanner;

public class ReadSpecificCurrency {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String argv[]) {

        ReadSpecificCurrency rs = new ReadSpecificCurrency();
        System.out.println("Įveskite valiutos koda:");
        String valiuta = scanner.next();
        System.out.println("Įveskite datą nuo:");
        String data1 = scanner.next();
        System.out.println("Įveskite datą iki:");
        String data2 = scanner.next();
        rs.specificValues(valiuta, data1, data2);

    }

    public void specificValues(String valiuta, String data1, String data2) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL("https://www.lb.lt/lt/currency/exportlist/?xml=1&currency=" + valiuta + "&ff=1&class=Eu&type=day&date_from_day=" + data1 + "&date_to_day=" + data2).openStream());

            doc.getDocumentElement().normalize();

            System.out.println();
            System.out.println(valiuta + " valiutos kursas pagal datas " + data1 + " - " + data2);

            NodeList nList = doc.getElementsByTagName("item");

            System.out.println("----------------------------");

            for (int temp = 1; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

//                    System.out.println("Staff id : " + eElement.getAttribute("item"));
                    if (temp ==1) {
                        System.out.println("Valiutos Pavadinimas : " + eElement.getElementsByTagName("pavadinimas").item(0).getTextContent());
                        System.out.println("Valiutos kodas : " + eElement.getElementsByTagName("valiutos_kodas").item(0).getTextContent());
                        System.out.println();
                    }

                    System.out.println("Santykis : " + eElement.getElementsByTagName("santykis").item(0).getTextContent());
                    System.out.println("Data : " + eElement.getElementsByTagName("data").item(0).getTextContent());
                    System.out.println();

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}