export function basicAuthToken() {
  let user = JSON.parse(localStorage.getItem('user'))

  if (user?.authData) {
    return 'Basic ' + user.authData
  } else {
    return ''
  }
}
