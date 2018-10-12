var mongoose = require("mongoose");

// var UserSchema = new mongoose.Schema({
//     username: String,
//     email: String,
//     startup: String,
//     idea: String,
//     linkdIn: String,
//     contract_addr: String,
//     amount_needed: String
// });

var UserSchema = new mongoose.Schema({
    username: String,
    email: String,
    walletAddress: String,
});


module.exports = mongoose.model("User", UserSchema);