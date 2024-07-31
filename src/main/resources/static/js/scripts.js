document.addEventListener('DOMContentLoaded', function () {
    function normalizeInput(input) {
        return input.replace(',', '.');
    }

    function formatDecimal(value) {
        return value !== null && !isNaN(value) ? value.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) : '0';
    }

    // Normaliza as entradas dos campos de texto
    var inputs = document.querySelectorAll('input[type="text"]');
    inputs.forEach(function (input) {
        input.addEventListener('input', function (event) {
            var value = event.target.value;
            event.target.value = normalizeInput(value);
        });
    });

    var metaTpvNovo = parseFloat(document.getElementById('metaTpvNovo').innerText.replace(',', '.'));
    var metaTpvLegado = parseFloat(document.getElementById('metaTpvLegado').innerText.replace(',', '.'));
    var metaMargemNovo = parseFloat(document.getElementById('metaMargemNovo').innerText.replace(',', '.'));
    var metaMargemLegado = parseFloat(document.getElementById('metaMargemLegado').innerText.replace(',', '.'));

    var resultadoMetaNovo = parseFloat(document.getElementById('resultadoMetaNovo').innerText.replace(',', '.'));
    var resultadoMetaLegado = parseFloat(document.getElementById('resultadoMetaLegado').innerText.replace(',', '.'));
    var resultadoMargemNovo = parseFloat(document.getElementById('resultadoMargemNovo').innerText.replace(',', '.'));
    var resultadoMargemLegado = parseFloat(document.getElementById('resultadoMargemLegado').innerText.replace(',', '.'));

    var projecaoMetaNovo = parseFloat(document.getElementById('projecaoMetaNovo').innerText.replace(',', '.'));
    var projecaoMetaLegado = parseFloat(document.getElementById('projecaoMetaLegado').innerText.replace(',', '.'));
    var projecaoMargemNovo = parseFloat(document.getElementById('projecaoMargemNovo').innerText.replace(',', '.'));
    var projecaoMargemLegado = parseFloat(document.getElementById('projecaoMargemLegado').innerText.replace(',', '.'));

    // Explicações para resultados abaixo de 70%
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

    // Formatação de valores das projeções e comissões
    document.getElementById('projecaoMetaNovo').innerText = formatDecimal(projecaoMetaNovo);
    document.getElementById('projecaoMetaLegado').innerText = formatDecimal(projecaoMetaLegado);
    document.getElementById('projecaoMargemNovo').innerText = formatDecimal(projecaoMargemNovo);
    document.getElementById('projecaoMargemLegado').innerText = formatDecimal(projecaoMargemLegado);
    document.getElementById('comissaoProjetada').innerText = formatDecimal(parseFloat(document.getElementById('comissaoProjetada').innerText));

    var ctx = document.getElementById('resultadoChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['TPV Novo', 'TPV Legado', 'Margem Novo', 'Margem Legado'],
            datasets: [
                {
                    label: 'Projeção',
                    data: [projecaoMetaNovo, projecaoMetaLegado, projecaoMargemNovo, projecaoMargemLegado],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)', // Verde claro
                    borderColor: 'rgba(75, 192, 192, 1)', // Verde escuro
                    borderWidth: 1
                },
                {
                    label: 'Meta',
                    data: [metaTpvNovo, metaTpvLegado, metaMargemNovo, metaMargemLegado],
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }
            ]
        },
        options: {
            indexAxis: 'y', // Altera o gráfico para barras horizontais
            scales: {
                x: {
                    beginAtZero: true,
                    type: 'logarithmic', // Configura o eixo X como escala logarítmica
                    ticks: {
                        callback: function(value, index, values) {
                            return value.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
                        }
                    }
                },
                y: {
                    stacked: true
                }
            }
        }
    });
});
