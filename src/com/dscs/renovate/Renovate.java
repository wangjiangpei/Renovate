package com.dscs.renovate;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class Renovate extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        InputDialog inputDialog = new InputDialog();
        inputDialog.setTitle("upload doc");
        inputDialog.setListener(new InputDialog.Listener() {
            @Override
            public void info(Info info) {
//                File file = new File(project.getBasePath() + "/doc.txt");
                File file = new File("D:/work/msg.xml");
                Element root = null;
                Document document = null;
                if (file.isFile()) {
                    //获得文档对象
                    document = XMLCurdByDom.getXMLDocument();
                    //创建一个新的节点
                    Element newNode = document.createElement("msg");
                    NodeList nl = document.getElementsByTagName("doc");
                    Element context = document.createElement("context"),
                            describe = document.createElement("describe");
                    newNode.setAttribute("name", info.name);
                    newNode.setAttribute("time", TimeFormat.nowTimeStr());
                    context.setTextContent(info.context);
                    describe.setTextContent(info.describe);
                    newNode.appendChild(context);
                    newNode.appendChild(describe);
                    nl.item(0).appendChild(newNode);
                } else {
                    try {
                        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = builderFactory.newDocumentBuilder();
                        document = builder.newDocument();
                        root = document.getDocumentElement();
                        root = document.createElement("doc");
                        Element student = document.createElement("msg");
                        Element context = document.createElement("context"),
                                describe = document.createElement("describe");
                        student.setAttribute("name", info.name);
                        student.setAttribute("time", TimeFormat.nowTimeStr());
                        context.setTextContent(info.context);
                        describe.setTextContent(info.describe);
                        student.appendChild(context);
                        student.appendChild(describe);
                        root.appendChild(student);
                        document.appendChild(root);
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                }

                XMLCurdByDom.saveXML(document);
            }
        });
        inputDialog.start();
    }

}
