package com.dscs.renovate;

import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;

import org.junit.Test;

import org.w3c.dom.Document;

import org.w3c.dom.Element;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

/**
 * 使用DOM对象对XML文件的增删改查
 *
 * @author Administrator
 */

public class XMLCurdByDom {

//获得DOM对象  

    public static Document getXMLDocument() {
        DocumentBuilder builder = null;
        Document document = null;
        try {
//调用 DocumentBuilderFactory.newInstance() 方法得到创建 DOM 解析器的工厂  
//调用工厂对象的 newDocumentBuilder方法得到 DOM 解析器对象
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//调用 DOM 解析器对象的 parse() 方法解析 XML 文档，得到代表整个文档的 Document 对象
            document = builder.parse("D:/work/msg.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 保存文档。将修改后的文档保存到文件中去
     *
     * @param document
     */
    public static void saveXML(Document document) {
        Transformer tf = null;
        try {
            tf = TransformerFactory.newInstance().newTransformer();
            tf.transform(new DOMSource(document), new StreamResult("D:/work/msg.xml"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 遍历所有节点测试
     */
    @Test
    public void testIterateNode() {
//首页获得XML文档对象。
        Document document = this.getXMLDocument();
        this.iterateNode(document);
    }

    //查询书本信息
    @Test
    public void queryBookInfo() {
//先得到xml文档对象
        Document document = this.getXMLDocument();
//得到所有节点为书名的一个集合
//NodeList接口提供对节点的有序集合的抽象，没有定义或约束如何实现此集合。DOM 中的 NodeList 对象是活动的。
        NodeList nl = document.getElementsByTagName("书名");
        //输出要获得的节点对象的内容
        System.out.println(nl.item(0).getTextContent());
    }

    /**
     * 添加节点，本例中在书节点下添加一个特价节点。
     */
    @Test
    public void addNode() {

//获得文档对象
        Document document = this.getXMLDocument();
//创建一个新的节点
        Node newNode = document.createElement("特价");
//获得<书名>节点
        NodeList nl = document.getElementsByTagName("书");
//设置文本节点
        newNode.setTextContent("100");
//添加子节点
        nl.item(0).appendChild(newNode);
        newNode.setTextContent("50");
        nl.item(1).appendChild(newNode);
//将修改后的文档保存到文件中去
        this.saveXML(document);
    }

    /**
     * 遍历所有节点
     *
     * @param node
     */
    public static void iterateNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(node.getNodeName());
        }
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            iterateNode(node.getChildNodes().item(i));
        }
    }

    /**
     * 修改第二本书的价格
     */
    @Test
    public void updateXml() {
//先得到文档对象
        Document document = this.getXMLDocument();
//等到第二本书价格所对应的节点。  

        Node node = document.getElementsByTagName("价格").item(1);

        node.setTextContent("100");

        this.saveXML(document);

    }

//删除节点  

    /**
     * 删除第二本书的特价信息
     */
    @Test
    public void deleteNode() {
//获得整个文档对象
        Document document = this.getXMLDocument();
//获得要删除的节点对象
        Node node = document.getElementsByTagName("特价").item(0);
//获得要删除节点对象的父节点，然后删除该节点
        node.getParentNode().removeChild(node);
//保存文档对象
        this.saveXML(document);
    }

    //在指定的节点前插入节点
    @Test
    public void insertNode() {
//获得整个文档对象
        Document document = this.getXMLDocument();
//获得要在其前面插入节点的节点对象
        Node node = document.getElementsByTagName("价格").item(0);
        //创建一个新的节点
        Node newChild = document.createElement("优惠价");
        newChild.setTextContent("56");
//插入节点对象
        node.getParentNode().insertBefore(newChild, node);
//保存文档对象
        this.saveXML(document);
    }

//修改书的属性值  

    @Test

    public void updateAttr() {
//先获得文档的根节点对象
        Document document = this.getXMLDocument();
        //获得要操作那个属性所对应的元素对象
        Element element = (Element) document.getElementsByTagName("书").item(0);
        element.setAttribute("出版社", "天宇出版社");
        this.saveXML(document);
    }

}  