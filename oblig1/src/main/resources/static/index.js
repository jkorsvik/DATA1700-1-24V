document.addEventListener('DOMContentLoaded', () => {
    const seatsContainer = document.getElementById('seats');
    const totalText = document.getElementById('total');
    let total = 0;
    const seatPrice = 10;
    const totalSeats = 32; // Example: 4 rows of 8 seats

    // Generate seats
    for (let i = 0; i < totalSeats; i++) {
        const seat = document.createElement('div');
        seat.classList.add('seat');
        seat.addEventListener('click', () => toggleSeatSelection(seat));
        seatsContainer.appendChild(seat);
    }

    function toggleSeatSelection(seat) {
        seat.classList.toggle('selected');
        updateTotal();
    }

    function updateTotal() {
        const selectedSeats = document.querySelectorAll('.seat.selected').length;
        total = selectedSeats * seatPrice;
        totalText.textContent = total;
    }
});
