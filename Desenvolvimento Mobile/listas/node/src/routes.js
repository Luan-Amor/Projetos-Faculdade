const express = require('express')
const routes = express.Router()

let valor = 0

routes.get('/', (req, res) => {
    res.sendFile(__dirname + '/exerc1.html')
})

routes.post('/result', (req, res) => {
    dados = req.body
    c = Object.values(dados).reduce( (e, i) => {
        val1 = parseInt(e)
        val2 = parseInt(i)
        return total = val1 + val2
    })
    valor = c
    res.redirect('/result')
})

routes.get('/result', (req, res) => {
    res.send("valor: " +valor)
})


routes.get('/2', (req, res) =>{
    res.sendFile(__dirname + '/exerc2.html')
})

routes.get('/resultado', (req, res) =>{
    let {v1, v2} = req.query
    valor = v1 - v2
    res.send("Resultado: " + valor)
})
routes.post('/resultado', (req, res) =>{
    let {v1, v2} = req.body
    valor = parseInt(v1) + parseInt(v2)
    res.send("Resultado: " + valor)
})

module.exports = routes