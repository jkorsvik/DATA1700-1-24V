class TicketManager {
  constructor () {
    this.tickets = []
    this.initializeEventListeners()
  }

  initializeEventListeners () {
    document
      .getElementById('kjop')
      .addEventListener('click', () => this.addTicket())
    document
      .getElementById('slett')
      .addEventListener('click', () => this.deleteAllTickets())
  }

  addTicket () {
    const film = document.getElementById('film').value
    const antall = document.getElementById('antall').value
    const fornavn = document.getElementById('fornavn').value
    const etternavn = document.getElementById('etternavn').value
    const telefon = document.getElementById('telefon').value
    const epost = document.getElementById('epost').value

    if (this.validateTicket(film, antall, fornavn, etternavn, telefon, epost)) {
      const ticket = { film, antall, fornavn, etternavn, telefon, epost }
      this.tickets.push(ticket)
      this.resetFormFields()
      this.displayTickets()
    }
  }

  validateTicket (film, antall, fornavn, etternavn, telefon, epost) {
    let isValid = true
    const epostRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
    const tlfRegex = /^\+\d{2}[\s?-]\d{8}$/

    isValid &= this.setError('film', film === 'velg', 'Må velge en film')
    isValid &= this.setError(
      'antall',
      antall === '' || Number(antall) < 1,
      'Må skrive inn et gyldig antall billetter'
    )
    isValid &= this.setError(
      'fornavn',
      fornavn === '',
      'Fornavn kan ikke være tomt'
    )
    isValid &= this.setError(
      'etternavn',
      etternavn === '',
      'Etternavner\t kan ikke være tomt'
    )
    isValid &= this.setError(
      'telefon',
      telefon === '' || !tlfRegex.test(telefon),
      'Må skrive inn et gyldig telefonnummer (+47 12345678)'
    )
    isValid &= this.setError(
      'epost',
      epost === '' || !epostRegex.test(epost),
      'Må skrive noe inn i epost og det må være gyldig'
    )

    return isValid
  }

  setError (field, condition, errorMessage) {
    console.log(field)  // debug
    const errorField = document.getElementById(`${field}Error`)
    if (condition) {
      errorField.innerHTML = errorMessage
      return false
    } else {
      errorField.innerHTML = ''
      return true
    }
  }

  resetFormFields () {
    document.getElementById('film').value = ''
    document.getElementById('antall').value = ''
    document.getElementById('fornavn').value = ''
    document.getElementById('etternavn').value = ''
    document.getElementById('telefon').value = ''
    document.getElementById('epost').value = ''
  }

  displayTickets () {
    let table =
      '<table><tr><th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>Telefon</th><th>Epost</th></tr>'
    this.tickets.forEach(ticket => {
      table += `<tr><td>${ticket.film}</td><td>${ticket.antall}</td><td>${ticket.fornavn}</td><td>${ticket.etternavn}</td><td>${ticket.telefon}</td><td>${ticket.epost}</td></tr>`
    })
    table += '</table>'
    document.getElementById('billetter').innerHTML = table
  }

  deleteAllTickets () {
    this.tickets = []
    document.getElementById('billetter').innerHTML = ''
  }
}

// Initialize the ticket manager when the document is ready
document.addEventListener('DOMContentLoaded', () => {
  new TicketManager()
})
