import getDados from "./getDados.js";

const params = new URLSearchParams(window.location.search);
const serieId = params.get('id');
const listaTemporadas = document.getElementById('temporadas-select');
const fichaSerie = document.getElementById('temporadas-episodios');
const fichaDescricao = document.getElementById('ficha-descricao');

// Função para carregar temporadas
function carregarTemporadas() {
    getDados(`/series/${serieId}/temporadas/todas`)
        .then(data => {
            const temporadasUnicas = [...new Set(data.map(temporada => temporada.season))];
            listaTemporadas.innerHTML = ''; // Limpa as opções existentes

            const optionDefault = document.createElement('option');
            optionDefault.value = '';
            optionDefault.textContent = 'Selecione a temporada'
            listaTemporadas.appendChild(optionDefault); 
           
            temporadasUnicas.forEach(temporada => {
                const option = document.createElement('option');
                option.value = temporada;
                option.textContent = temporada;
                listaTemporadas.appendChild(option);
            });

            const optionTodos = document.createElement('option');
            optionTodos.value = 'todas';
            optionTodos.textContent = 'Todas as temporadas'
            listaTemporadas.appendChild(optionTodos);  

            const optionTop = document.createElement('option');
            optionTop.value = 'top';
            optionTop.textContent = 'Top 5 episódios'
            listaTemporadas.appendChild(optionTop);  
        })
        .catch(error => {
            console.error('Erro ao obter temporadas:', error);
        });
}

// Função para carregar episódios de uma temporada
function carregarEpisodios() {
    getDados(`/series/${serieId}/temporadas/${listaTemporadas.value}`)
        .then(data => {
            const temporadasUnicas = [...new Set(data.map(temporada => temporada.season))];
            fichaSerie.innerHTML = ''; 
            temporadasUnicas.forEach(temporada => {
                const ul = document.createElement('ul');
                ul.className = 'episodios-lista';

                const episodiosTemporadaAtual = data.filter(serie => serie.season === temporada);

                const listaHTML = episodiosTemporadaAtual.map(serie => `
                    <li>
                        ${serie.episodeNumber} - ${serie.title}
                    </li>
                `).join('');
                ul.innerHTML = listaHTML;
                
                const paragrafo = document.createElement('p');
                const linha = document.createElement('br');
                paragrafo.textContent = `Temporada ${temporada}`;
                fichaSerie.appendChild(paragrafo);
                fichaSerie.appendChild(linha);
                fichaSerie.appendChild(ul);
            });
        })
        .catch(error => {
            console.error('Erro ao obter episódios:', error);
        });
}

// Função para carregar top episódios da série
function carregarTopEpisodios() {
    getDados(`/series/${serieId}/temporadas/top`)
    .then(data => {
        fichaSerie.innerHTML = ''; 
            const ul = document.createElement('ul');
            ul.className = 'episodios-lista';

            const listaHTML = data.map(serie => `
                <li>
                    Episódio ${serie.episodeNumber} - Temporada ${serie.season} - ${serie.title}
                </li>
            `).join('');
            ul.innerHTML = listaHTML;
            
            const paragrafo = document.createElement('p');
            const linha = document.createElement('br');
            fichaSerie.appendChild(paragrafo);
            fichaSerie.appendChild(linha);
            fichaSerie.appendChild(ul);

        })
    .catch(error => {
        console.error('Erro ao obter episódios:', error);
    });
}

// Função para carregar informações da série
function carregarInfoSerie() {
    getDados(`/series/${serieId}`)
        .then(data => {
            fichaDescricao.innerHTML = `
                <img src="${data.poster}" alt="${data.title}" />
                <div>
                    <h2>${data.title}</h2>
                    <div class="descricao-texto">
                        <p><b>Média de avaliações:</b> ${data.rating}</p>
                        <p>${data.plot}</p>
                        <p><b>Estrelando:</b> ${data.actors}</p>
                    </div>
                </div>
            `;
        })
        .catch(error => {
            console.error('Erro ao obter informações da série:', error);
        });
}

// Adiciona ouvinte de evento para o elemento select
listaTemporadas.addEventListener('change', () => {
    const valorSelecionado = listaTemporadas.value;

    fichaSerie.innerHTML = ''; // Limpa o conteúdo antes de carregar nova exibição

    if (valorSelecionado === 'top') {
        carregarTopEpisodios();
    } else if (valorSelecionado === 'todas' || valorSelecionado !== '') {
        carregarEpisodios();
    }
});

// Carrega as informações da série e as temporadas quando a página carrega
carregarInfoSerie();
carregarTemporadas();
