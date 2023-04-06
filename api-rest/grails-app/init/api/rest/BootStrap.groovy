package api.rest

class BootStrap {

    def init = { servletContext ->
        new Product(name: "World of Warcraft", sku: "wow01", price: 39.99).save()
        new Product(name: "World of Warcraft Burning Crusade", sku: "wow02", price: 39.99).save()
        new Customer(firstName: "Matias", lastName: "De la Cerda", email: "mdelacerda@css.cl", phone: 978066123).save()
    }
    def destroy = {
    }
}
