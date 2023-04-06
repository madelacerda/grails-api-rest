package api.rest

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class CustomerOrderController {

    CustomerOrderService customerOrderService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond customerOrderService.list(params), model:[customerOrderCount: customerOrderService.count()]
    }

    def show(Long id) {
        respond customerOrderService.get(id)
    }

    @Transactional
    def save(CustomerOrder customerOrder) {
        if (customerOrder == null) {
            render status: NOT_FOUND
            return
        }
        if (customerOrder.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond customerOrder.errors
            return
        }

        try {
            customerOrderService.save(customerOrder)
        } catch (ValidationException e) {
            respond customerOrder.errors
            return
        }

        respond customerOrder, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(CustomerOrder customerOrder) {
        if (customerOrder == null) {
            render status: NOT_FOUND
            return
        }
        if (customerOrder.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond customerOrder.errors
            return
        }

        try {
            customerOrderService.save(customerOrder)
        } catch (ValidationException e) {
            respond customerOrder.errors
            return
        }

        respond customerOrder, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || customerOrderService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
