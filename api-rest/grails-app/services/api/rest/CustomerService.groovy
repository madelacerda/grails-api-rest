package api.rest

import grails.gorm.services.Service

@Service(Customer)
interface CustomerService {

    Customer get(Serializable id)

    List<Customer> list(Map args)

    Long count()

    Customer delete(Serializable id)

    Customer save(Customer customer)

}
