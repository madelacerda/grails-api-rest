package api.rest.boot

import api.rest.Customer
import api.rest.LoginUser
import api.rest.LoginUserRole
import api.rest.Product
import api.rest.Role
import grails.gorm.transactions.Transactional

@Transactional
class BootService {

    def createProducts() {
//        new Product(name: "World of Warcraft", sku: "wow01", price: 39.99).save()
//        new Product(name: "World of Warcraft Burning Crusade", sku: "wow02", price: 39.99).save()
//        new Customer(firstName: "Matias", lastName: "De la Cerda", email: "mdelacerda@css.cl", phone: 978066123).save()
    }

    def createUsers() {
        LoginUser admin = new LoginUser(username: 'admin', password: 'abc12345').save()
        Role role = new Role(authority: 'ROLE_ADMIN').save()
        LoginUserRole.create(admin, role)
    }
}
