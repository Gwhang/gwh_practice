package com.example.demo.test;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest9 {

    public static void main(String[] args) {

        List<ProcessNode> processNodeList=getProcessNodeList();

        /**
         * 过滤某些节点并转换为新的list返回
         */
        List<Node> nodes = processNodeList.stream().filter(p->!p.getCode().equals("1002")).
                filter(p -> !p.getCode().equals("1003")).map(p -> {
            Node node = new Node();
            node.setNodeCode(p.getCode());
            node.setNodeName(p.getName());
            node.setNodeStatus(p.getStatus());
            return node;
        }).collect(Collectors.toList());

        System.out.println(JSONObject.toJSONString(nodes));

    }

    public static List<ProcessNode> getProcessNodeList(){

        List<ProcessNode> processNodeList = new ArrayList<ProcessNode>();

        ProcessNode processNode1=new ProcessNode("1","1001","请假条提交","PASS","通过",new Date());
        ProcessNode processNode2=new ProcessNode("2","1002","班委审批","PASS","通过",new Date());
        ProcessNode processNode3=new ProcessNode("3","1003","班主任审批","PASS","通过",new Date());
        ProcessNode processNode4=new ProcessNode("4","1004","年级主任审批","PASS","通过",new Date());
        ProcessNode processNode5=new ProcessNode("5","1005","校长审批","PASS","通过",new Date());

        processNodeList.add(processNode1);
        processNodeList.add(processNode2);
        processNodeList.add(processNode3);
        processNodeList.add(processNode4);
        processNodeList.add(processNode5);

        return processNodeList;
    }

}

/**
 * 申请节点详情
 */
class ProcessNode{

    /**
     * 流程ID
     */
    private String id;

    /**
     * 节点编码
     */
    private String code;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 审批状态
     */
    private String status;
    /**
     * 审批结果
     */
    private String result;
    /**
     * 创建日期
     */
    private Date create;

    public ProcessNode() {
    }

    public ProcessNode(String id, String code, String name, String status, String result, Date create) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.status = status;
        this.result = result;
        this.create = create;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}

class Node{

    private String nodeCode;

    private String nodeName;

    private String nodeStatus;

    public Node() {
    }

    public Node(String nodeCode, String nodeName, String nodeStatus) {
        this.nodeCode = nodeCode;
        this.nodeName = nodeName;
        this.nodeStatus = nodeStatus;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }
}