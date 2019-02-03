export const translateEn = {
  alerts: {
    serverError: 'Application Error',
    backend: {
      constraints: {
        class: {
          BcryptDataConstraint: 'data should be between 0 and 100 characters',
          BcryptIterationConstraint: 'Iterations should be a number between 4 and 15'
        }
      }
    }
  },
  shared: {
    copyToClipboard: {
      copied: 'Copied',
      copyToClipboard: 'Copy to Clipboard'
    }
  },
  menu: {
    base64Encoder: 'Base64 Encoder',
    base64Decoder: 'Base64 Decoder',
    bcryptPassword: 'BCrypt Password Encoder',
    passwordGenerator: 'Password Generator',
    uuidGenerator: 'UUID Generator',
    pkiKeyGenerator: 'PKI Key Generator'
  },
  pages: {
    pkiKeyGenerator: {
      pageHeader: 'PKI Key Generator',
      panelHeading: 'PKI Key Configuration',
      form: {
        keySize: 'Length of the key',
        algorithm: 'Algorithm',
        submit: 'Generate'
      },
      results: {
        heading: 'Generated Public/Private Key',
        publicKeyHeading: 'Public Key',
        privateKeyHeading: 'Private Key',
        downloadData: 'Download'
      }
    },
    uuidGenerator: {
      pageHeader: 'UUID Generator',
      panelHeading: 'Generate',
      form: {
        generateType4: 'Generate type 4 UUID'
      },
      results: {
        panelHeading: 'Generated UUIDs',
        copied: 'Copied',
        copyToClipboard: 'Copy to clipboard'
      }
    },
    passwordGenerator: {
      pageHeader: 'Password Generator',
      panelHeading: 'Password Configuration',
      form: {
        config: {
          title: 'Characters',
          useCapitalLetters: 'Use capital letters (A, B, C, ...)',
          useSmallLetters: 'Use small letters (a, b, c, ...)',
          useNumbers: 'Use numbers (0, 1, 2, 3, ...)',
          length: 'Length of password',
          submit: 'Generate'
        }
      },
      results: {
        panelHeading: 'Generated Passwords',
        noData: 'No passwords are generated at this point'
      }
    },
    base64Decoder: {
      pageHeader: 'Base64 Decoder',
      pageHeading: 'Decoding data',
      form: {
        data: 'Data',
        submit: 'Decode'
      }
    },
    base64Encoder: {
      pageTitle: 'Base64 Encoder',
      pageHeading: 'Encoding Data',
      form: {
        submit: 'Encode'
      },
      result: {
        title: 'Encoding Result',
        data: 'Data',
        downloadData: 'Download data',
        value: 'Value',
        downloadResult: 'Download result'
      }
    },
    bcryptPassword: {
      pageHeader: 'BCrypt Password Encoder',
      panelHeading: 'Encoding Data',
      form: {
        iterations: 'Iterations',
        iterationsHelpBlock: 'number of iterations',
        data: 'Data',
        dataHelpBlock: 'Data to encode',
        submit: 'Encode'
      },
      result: {
        title: 'Result',
        password: 'Password:',
        iterations: 'Iterations:',
        encodedData: 'Encoded data'
      }
    }
  }
};
