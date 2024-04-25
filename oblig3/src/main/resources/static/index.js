const backend_url_ip = 'http://127.0.0.1:8080'
//const backend_url = 'http://localhost:8080'
const backend_url = 'https://studious-system-7px779946wxcw6w7-8080.app.github.dev'

class TicketManager {
  constructor() {
    this.tickets = []
    this.initializeEventListeners()
  }

  initializeEventListeners() {
    document
      .getElementById('kjop')
      .addEventListener('click', () => this.addTicket()) // 
    document
      .getElementById('slett')
      .addEventListener('click', () => this.deleteAllTickets()) // Delete all tickets 
  }

  addTicket() {
    let film = document.getElementById('film').value
    let antall = document.getElementById('antall').value
    let fornavn = document.getElementById('fornavn').value
    let etternavn = document.getElementById('etternavn').value
    let telefon = document.getElementById('telefon').value
    let epost = document.getElementById('epost').value

    if (this.validateTicket(film, antall, fornavn, etternavn, telefon, epost)) {
      const ticket = { film, antall, fornavn, etternavn, telefon, epost }
      this.postTicket(ticket);
    }
  }

  async getTickets() {
    try {
      const response = await fetch(backend_url + '/get_tickets');
      if (!response.ok) {
        throw new Error('Failed to fetch tickets');
      }
      const data = await response.json();
      this.tickets = data; // assuming data is an array of ticket objects
      this.displayTickets();
    } catch (error) {
      console.error('Error:', error);
    }
  }

  async postTicket(ticket) {
    try {
      const response = await fetch(backend_url + '/post_tickets', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(ticket),
      });
      if (!response.ok) {
        throw new Error('Failed to add ticket');
      }
      const data = await response.json();
      this.tickets.push(data);
      this.resetFormFields();
      this.displayTickets();
    } catch (error) {
      console.error('Error:', error);
    }
  }

  async deleteTicket(ticketId) {
    try {
      const response = await fetch(backend_url + `/tickets/${ticketId}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to delete ticket');
      }
      this.tickets = this.tickets.filter(ticket => ticket.id !== ticketId);
      this.displayTickets();
    } catch (error) {
      console.error('Error:', error);
    }
  }


  validateTicket(film, antall, fornavn, etternavn, telefon, epost) {
    let isValid = true
    // Regex for epost and telefon number
    // Source: https://emailregex.com/ 
    const epostRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
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
      'Ikke en gyldig epostadresse (abc@abc.com)'
    )

    return isValid
  }

  setError(field, condition, errorMessage) {
    const errorField = document.getElementById(`${field}Error`)
    const inputField = document.getElementById(field)
    if (condition) {
      errorField.innerHTML = errorMessage
      inputField.classList.add('error')
      return false
    } else {
      errorField.innerHTML = ''
      inputField.classList.remove('error')
      return true
    }
  }

  resetFormFields() {
    document.getElementById('film').value = ''
    document.getElementById('antall').value = ''
    document.getElementById('fornavn').value = ''
    document.getElementById('etternavn').value = ''
    document.getElementById('telefon').value = ''
    document.getElementById('epost').value = ''
  }

  displayTickets() {
    let table =
      '<table><tr><th>ID</th><th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>Telefon</th><th>Epost</th></tr>'
    this.tickets.forEach(ticket => {
      table += `<tr><td>${ticket.id}</td><td>${ticket.film}</td><td>${ticket.antall}</td><td>${ticket.fornavn}</td><td>${ticket.etternavn}</td><td>${ticket.telefon}</td><td>${ticket.epost}</td></tr>`
    })
    table += '</table>'
    document.getElementById('billetter').innerHTML = table
  }

  searchTicketById(id) {
    const ticket = this.tickets.find(ticket => ticket.id === id);
    const resultLabel = document.getElementById('searchResult');
    if (ticket) {
      resultLabel.innerHTML = `Billett funnet: ${JSON.stringify(ticket)}`;
    } else {
      resultLabel.innerHTML = 'Ingen billett funnet med gitt ID';
    }
  }

  deleteAllTickets() {
    this.tickets = [];
    document.getElementById('billetter').innerHTML = '';
    document.getElementById('slett').disabled = true; // Disable the button when there are no tickets
  }
}

// Initialize the ticket manager when the document is ready
document.addEventListener('DOMContentLoaded', () => {
  const ticketManager = new TicketManager();
  ticketManager.getTickets(); // Fetch tickets when the page loads
  // Add event listener for the "Slett alle billettene" button
  document.getElementById('slett').addEventListener('click', () => {
    ticketManager.deleteAllTickets();
  });

  // Add event listener for the "Søk" button
  document.getElementById('search').addEventListener('click', () => {
    const ticketId = document.getElementById('ticketId').value;
    ticketManager.searchTicketById(ticketId);
  });
})
