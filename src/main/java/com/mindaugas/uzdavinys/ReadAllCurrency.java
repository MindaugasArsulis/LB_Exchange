package com.mindaugas.uzdavinys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.Scanner;

public class ReadAllCurrency {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String argv[]) {

        ReadAllCurrency rd = new ReadAllCurrency();
        System.out.println("Įveskite datą:");
        String entered = scanner.next();
        rd.allValues(entered);

    }

    public void allValues(String data) {
        try {

//            File fXmlFile = new File("C:\\Users\\minda\\Downloads\\currency-2019-01-08.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL("https://www.lb.lt/lt/currency/daylyexport/?xml=1&class=Eu&type=day&date_day=" + data).openStream());

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println();
            System.out.println("Valiutų kursai pagal datą " + data);

            NodeList nList = doc.getElementsByTagName("item");

            System.out.println("----------------------------");

            for (int temp = 1; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

//                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

//                    System.out.println("Staff id : " + eElement.getAttribute("item"));
                    System.out.println("Valiutos Pavadinimas : " + eElement.getElementsByTagName("pavadinimas").item(0).getTextContent());
                    System.out.println("Valiutos kodas : " + eElement.getElementsByTagName("valiutos_kodas").item(0).getTextContent());
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


