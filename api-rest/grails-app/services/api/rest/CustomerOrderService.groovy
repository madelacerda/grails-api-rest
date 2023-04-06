package api.rest

import grails.gorm.services.Service

@Service(CustomerOrder)
interface CustomerOrderService {

    CustomerOrder get(Serializable id)

    List<CustomerOrder> list(Map args)

    Long count()

    CustomerOrder delete(Serializable id)

    CustomerOrder save(CustomerOrder customerOrder)

}
