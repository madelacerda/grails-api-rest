package api.rest

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class ProductController {

    ProductService productService

    static responseFormats = ['json', 'xml']

//    @Secured(value = ["permitAll"], httpMethod = 'GET')
    def index(Integer max) {
        params.max = -1
        respond productService.list(params), model:[productCount: productService.count()]
    }

    def show(Long id) {
        respond productService.get(id)
    }

    @Transactional
//    @Secured(value = ['ROLE_ADMIN'], httpMethod = 'POST')
    def save(Product product) {
        if (product == null) {
            render status: NOT_FOUND
            return
        }
        if (product.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond product.errors
            return
        }

        try {
            productService.save(product)
        } catch (ValidationException e) {
            respond product.errors
            return
        }

        respond product, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Product product) {
        if (product == null) {
            render status: NOT_FOUND
            return
        }
        if (product.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond product.errors
            return
        }

        try {
            productService.save(product)
        } catch (ValidationException e) {
            respond product.errors
            return
        }

        respond product, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || productService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
