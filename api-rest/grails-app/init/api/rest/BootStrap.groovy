package api.rest

import api.rest.boot.BootService

class BootStrap {

    BootService bootService

    def init = { servletContext ->
        bootService.createProducts()
        bootService.createUsers()
    }
    def destroy = {
    }
}
