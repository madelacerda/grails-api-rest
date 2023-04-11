package api.rest

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class LoginUserRole implements Serializable {

	private static final long serialVersionUID = 1

	LoginUser loginUser
	Role role

	@Override
	boolean equals(other) {
		if (other instanceof LoginUserRole) {
			other.loginUserId == loginUser?.id && other.roleId == role?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (loginUser) {
            hashCode = HashCodeHelper.updateHash(hashCode, loginUser.id)
		}
		if (role) {
		    hashCode = HashCodeHelper.updateHash(hashCode, role.id)
		}
		hashCode
	}

	static LoginUserRole get(long loginUserId, long roleId) {
		criteriaFor(loginUserId, roleId).get()
	}

	static boolean exists(long loginUserId, long roleId) {
		criteriaFor(loginUserId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long loginUserId, long roleId) {
		LoginUserRole.where {
			loginUser == LoginUser.load(loginUserId) &&
			role == Role.load(roleId)
		}
	}

	static LoginUserRole create(LoginUser loginUser, Role role, boolean flush = false) {
		def instance = new LoginUserRole(loginUser: loginUser, role: role)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(LoginUser u, Role r) {
		if (u != null && r != null) {
			LoginUserRole.where { loginUser == u && role == r }.deleteAll()
		}
	}

	static int removeAll(LoginUser u) {
		u == null ? 0 : LoginUserRole.where { loginUser == u }.deleteAll() as int
	}

	static int removeAll(Role r) {
		r == null ? 0 : LoginUserRole.where { role == r }.deleteAll() as int
	}

	static constraints = {
	    loginUser nullable: false
		role nullable: false, validator: { Role r, LoginUserRole ur ->
			if (ur.loginUser?.id) {
				if (LoginUserRole.exists(ur.loginUser.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['loginUser', 'role']
		version false
	}
}
