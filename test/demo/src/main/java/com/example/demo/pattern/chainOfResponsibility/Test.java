package com.example.demo.pattern.chainOfResponsibility;

/**
 * 职责链模式（Chain of Responsibility Pattern）又叫责任链模式，属于行为型模式。
 * 它为请求创建了一个接受者对象的链，每个接受者都包含对另一个接受者的引用。
 * 如果一个对象不能处理该请求，那么它会把相同的请求传给下一个接受者，依次类推。
 */
public class Test {

    /**
     * 在学校中报销一笔费用，如果费用小于 1000 块，系主任签字就可以报销。如果费用大于 1000 小于 10000 块则需要院长签字才能报销。
     * 如果费用大于一万块则需要校长签字报销。现在用代码实现报销流程
     * @param args
     */
    public static void main(String[] args) {
        // 构造一个请求
        PurchaseRequest purchaseRequest0 = new PurchaseRequest(1, 500, 1);
        PurchaseRequest purchaseRequest1 = new PurchaseRequest(1, 1100, 2);
        PurchaseRequest purchaseRequest2 = new PurchaseRequest(1, 50000, 3);
        //创建相关审核人
        DepartmentHandler department = new DepartmentHandler("张主任");
        DeanHandler dean = new DeanHandler("李院长");
        SchoolMasterHandler schoolMaster = new SchoolMasterHandler("王校长");

        //将各审批人的下一个级别设置好
        department.setNextHandler(dean);
        dean.setNextHandler(schoolMaster);
        schoolMaster.setNextHandler(department);

        department.processRequest(purchaseRequest0);
        department.processRequest(purchaseRequest1);
        department.processRequest(purchaseRequest2);
    }

}
