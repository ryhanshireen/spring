// test.js
const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('./app'); 
const expect = chai.expect;

chai.use(chaiHttp);


  describe('POST /v1/hospital', () => {
    it('should create a new hospital record', (done) => {
      chai.request(app)
        .post('/v1/hospital')
        .send({
          name: 'John Doe',
          dob: '01-01-1980',
          username: 'john@example.com'
        })
        .end((err, res) => {
          expect(res).to.have.status(200);
          expect(res.body).to.be.an('object');
          expect(res.body).to.have.property('id');
          expect(res.body).to.have.property('name', 'John Doe');
          expect(res.body).to.have.property('dob', '01-01-1980');
          expect(res.body).to.have.property('doj');

          done();
        });
    });

    it('should return an error if username already exists', (done) => {
      chai.request(app)
        .post('/v1/hospital')
        .send({
          name: 'Jane Doe',
          dob: '02-02-1990',
          username: 'existing@example.com' 
        })
        .end((err, res) => {
          expect(res).to.have.status(400);
          expect(res.body).to.be.an('object');
          expect(res.body).to.have.property('error', 'Username already exists');

          done();
        });
    });

    it('should return an error if request body is invalid', (done) => {
      chai.request(app)
        .post('/v1/hospital')
        .send({
          name: 'Jane Doe',
          dob: '02-02-1990'
        })
        .end((err, res) => {
          expect(res).to.have.status(400);
          expect(res.body).to.be.an('object');
          expect(res.body).to.have.property('error');

          done();
        });
    });
  });

