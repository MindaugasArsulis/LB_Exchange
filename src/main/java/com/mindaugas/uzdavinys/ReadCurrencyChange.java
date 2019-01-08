package com.mindaugas.uzdavinys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Scanner;

public class ReadCurrencyChange {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ReadCurrencyChange rc = new ReadCurrencyChange();

        System.out.println("Įveskite valiutos koda:");
        String valiuta = scanner.next();
        System.out.println("Įveskite datą nuo:");
        String data1 = scanner.next();
        System.out.println("Įveskite datą iki:");
        String data2 = scanner.next();
        rc.currency(valiuta, data1, data2);

    }



    public void currency (String valiuta, String data1, String data2) {

        double pirmas = 0;

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL("https://www.lb.lt/lt/currency/exportlist/?xml=1&currency=" + valiuta + "&ff=1&class=Eu&type=day&date_from_day=" + data1 + "&date_to_day=" + data2).openStream());

            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("item");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    double antras;
                    if (temp == 1) {
                        String s = eElement.getElementsByTagName("santykis").item(0).getTextContent();
                        s = s.replace(',', '.');
                        pirmas = Double.parseDouble(s);
                        System.out.println("Valiutos Pavadinimas : " + eElement.getElementsByTagName("pavadinimas").item(0).getTextContent());
                        System.out.println("Valiutos kodas : " + eElement.getElementsByTagName("valiutos_kodas").item(0).getTextContent());
                        System.out.println("Naujesnis kursas: " + pirmas);
                    }
                    if(temp == nList.getLength() - 1) {
                        String s = eElement.getElementsByTagName("santykis").item(0).getTextContent();
                        s = s.replace(',', '.');
                        antras = Double.parseDouble(s);
                        System.out.println("Senesnis kursas: " + antras);
                        BigDecimal vienas = BigDecimal.valueOf(pirmas);
                        BigDecimal du = BigDecimal.valueOf(antras);
                        System.out.println("Pokytis " + vienas.subtract(du));

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
