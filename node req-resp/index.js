const express = require('express');
const Joi = require('joi');
const app = express();
const PORT = 3000;

app.use(express.json());

const existingUsernames = ['existing@example.com']; 
const schema = Joi.object({
  name: Joi.string().pattern(/^[a-zA-Z]+$/).required(),
  dob: Joi.string().pattern(/^\d{2}-\d{2}-\d{4}$/).required(),
  username: Joi.string().email().required()
});

app.post('/v1/hospital', (req, res) => {
  try {
    const { error, value } = schema.validate(req.body);

    if (error) {
      throw new Error(error.details[0].message);
    }

    if (existingUsernames.includes(value.username)) {
      throw new Error('Username already exists');
    }

    const response = {
      id: generateId(),
      name: value.name,
      dob: value.dob,
      doj: getCurrentDate()
    };

    
    existingUsernames.push(value.username);

    res.json(response);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});

let counter = 1;
function generateId() {
  return counter++;
}

function getCurrentDate() {
  const today = new Date();
  const dd = String(today.getDate()).padStart(2, '0');
  const mm = String(today.getMonth() + 1).padStart(2, '0');
  const yyyy = today.getFullYear();

  return dd + '-' + mm + '-' + yyyy;
}
