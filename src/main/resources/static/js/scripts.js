document.addEventListener('DOMContentLoaded', function() {
    function formatDecimal(value) {
        return value !== null && !isNaN(value) ? value.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) : '0';
    }

    var resultadoMetaNovo = parseFloat(document.getElementById('resultadoMetaNovo').innerText.replace(',', '.'));
    var resultadoMetaLegado = parseFloat(document.getElementById('resultadoMetaLegado').innerText.replace(',', '.'));
    var resultadoMargemNovo = parseFloat(document.getElementById('resultadoMargemNovo').innerText.replace(',', '.'));
    var resultadoMargemLegado = parseFloat(document.getElementById('resultadoMargemLegado').innerText.replace(',', '.'));

    var explicacaoDiv = document.getElementById('explicacao');
    if (resultadoMetaNovo < 70) {
        explicacaoDiv.innerHTML += '<p>O resultado de TPV Novo é menor que 70% e foi considerado 0% no cálculo da comissão.</p>';
    }
    if (resultadoMetaLegado < 70) {
        explicacaoDiv.innerHTML += '<p>O resultado de TPV Legado é menor que 70% e foi considerado 0% no cálculo da comissão.</p>';
    }
    if (resultadoMargemNovo < 70) {
        explicacaoDiv.innerHTML += '<p>O resultado de Margem Novo é menor que 70% e foi considerado 0% no cálculo da comissão.</p>';
    }
    if (resultadoMargemLegado < 70) {
        explicacaoDiv.innerHTML += '<p>O resultado de Margem Legado é menor que 70% e foi considerado 0% no cálculo da comissão.</p>';
    }

    document.getElementById('projecaoMetaNovo').innerText = formatDecimal(parseFloat(document.getElementById('projecaoMetaNovo').innerText));
    document.getElementById('projecaoMetaLegado').innerText = formatDecimal(parseFloat(document.getElementById('projecaoMetaLegado').innerText));
    document.getElementById('projecaoMargemNovo').innerText = formatDecimal(parseFloat(document.getElementById('projecaoMargemNovo').innerText));
    document.getElementById('projecaoMargemLegado').innerText = formatDecimal(parseFloat(document.getElementById('projecaoMargemLegado').innerText));
    document.getElementById('comissaoProjetada').innerText = formatDecimal(parseFloat(document.getElementById('comissaoProjetada').innerText));

    var ctx = document.getElementById('resultadoChart').getContext('2d');
    var chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['TPV Novo', 'TPV Legado', 'Margem Novo', 'Margem Legado'],
            datasets: [{
                label: 'Resultado (%)',
                data: [resultadoMetaNovo, resultadoMetaLegado, resultadoMargemNovo, resultadoMargemLegado],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var ctxComissao = document.getElementById('comissaoChart').getContext('2d');
    var comissaoChart = new Chart(ctxComissao, {
        type: 'pie',
        data: {
            labels: ['TPV Novo', 'TPV Legado', 'Margem Novo', 'Margem Legado'],
            datasets: [{
                label: 'Comissão (%)',
                data: [
                    resultadoMetaNovo < 70 ? 0 : resultadoMetaNovo,
                    resultadoMetaLegado < 70 ? 0 : resultadoMetaLegado,
                    resultadoMargemNovo < 70 ? 0 : resultadoMargemNovo,
                    resultadoMargemLegado < 70 ? 0 : resultadoMargemLegado
                ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true
        }
    });
});
