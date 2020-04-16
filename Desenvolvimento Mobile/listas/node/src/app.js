const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const routes = require('./routes')

app.engine('html', require('ejs').renderFile)
app.use(bodyParser.urlencoded({extended: false}))

app.use(routes)

app.listen(3000, () => {
    console.log('The server running on port 3000')
})

