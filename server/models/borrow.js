var mongoose = require("mongoose");

var Borrower_Schema = new mongoose.Schema({
    id: String,
    lenders: [String],
});


module.exports = mongoose.model("Borrower", Borrower_Schema);