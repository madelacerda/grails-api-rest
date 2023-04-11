// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'api.rest.LoginUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'api.rest.LoginUserRole'
grails.plugin.springsecurity.authority.className = 'api.rest.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/', access: ['permitAll']],
        [pattern: '/error', access: ['permitAll']],
        [pattern: '/**', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/**', filters: 'JOINED_FILTERS,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter']
]
