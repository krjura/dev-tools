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
    },
    downloadBinary: {
      downloadResult: 'Download'
    }
  },
  menu: {
    base64Encoder: 'Base64 Encoder',
    base64Decoder: 'Base64 Decoder',
    bcryptPassword: 'BCrypt Password Encoder',
    passwordGenerator: 'Password Generator',
    uuidGenerator: 'UUID Generator',
    pkiKeyGenerator: 'PKI Key Generator',
    hrOib: 'HR OIB',
    jsonpf: "JSON Formatter"
  },
  pages: {
    hroib: {
      pageHeader: 'HR OIB Tools',
      generator: {
        heading: 'OIB Generator',
        form: {
          generatorOib: 'Generate new OIB'
        }
      },
      generatorResults: {
        heading: 'Generated OIBs',
      },
      validator: {
        heading: 'Validate OIB',
        form: {
          oibHelpBlock: 'Please enter OIB value'
        }
      }
    },
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
          useSpecial: 'Use special characters (#, $, ...)',
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
        dataHelpBlock: 'Please enter data to decode',
        outputFormatLabel: 'Output type',
        outputFormatHelpBlock: 'Please enter output format',
        outputFilenameLabel: 'Filename',
        outputFilenameHelpBlock: 'Filename (characters, numbers, -, .)',
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
        value: 'Value',
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
    },
    jsonpf: {
      pageHeader: 'JSON Formatter',
      panelHeading: 'Raw JSON',
      form: {
        data: "Raw JSON",
        dataHelpBlock: "Please enter JSON data!",
        submit: "Format"
      },
      result: {
        title: "Formatted JSON data"
      }
    }
  }
};
