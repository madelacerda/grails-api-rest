package api.rest

class CustomerOrder {
    Date orderDate
    Integer orderNumber
    Float orderTotal
    static belongsTo = [customer:Customer]
    static constraints = {
        orderNumber(nullable: false)
        orderDate(nullable: false)
        orderTotal(nullable: false)
    }
}
